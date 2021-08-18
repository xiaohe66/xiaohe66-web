package com.xiaohe66.web.domain.rest.service;

import com.xiaohe66.web.domain.rest.repository.RestServiceRepository;
import com.xiaohe66.web.domain.rest.value.RestRepositoryDefinition;
import com.xiaohe66.web.integration.domain.MsgException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @since 2021.08.13 14:21
 */
@RequiredArgsConstructor
@Service
public class RestService implements ApplicationContextAware {

    private final Map<String, RestServiceRepository> repositoryMap = new HashMap<>();

    public long save(String tableAlias, Map<String, Object> data) {
        if (!repositoryMap.containsKey(tableAlias)) {
            throw new MsgException("数据类型不存在");
        }

        RestServiceRepository repository = repositoryMap.get(tableAlias);
        RestRepositoryDefinition definition = repository.getRestRepositoryDefinition();

        return -1;
    }

    public void remove(String tableAlias, long id) {

    }

    public void modifyById(String tableAlias, Map<String, Object> data) {

    }

    public Object getById(String tableAlias, long id) {
        return null;
    }

    public Object page(String tableAlias, long pageNo, long pageSize) {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        Map<String, RestServiceRepository> map = applicationContext.getBeansOfType(RestServiceRepository.class);

        map.forEach((k, repository) -> {
            RestRepositoryDefinition restRepositoryDefinition = repository.getRestRepositoryDefinition();

            String name = restRepositoryDefinition.getRepositoryName();
            if (repositoryMap.containsKey(name)) {
                throw new IllegalStateException("存在2个相同的restRepository : " + name);
            }

            repositoryMap.put(restRepositoryDefinition.getRepositoryName(), repository);
        });
    }
}
