package com.xiaohe66.web.domain.rest.value;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.08.13 15:39
 */
@Data
@EqualsAndHashCode
public class RestRepositoryDefinition {

    private final String repositoryName;

    public RestRepositoryDefinition(String repositoryName) {
        if (repositoryName == null || repositoryName.length() == 0) {
            throw new IllegalArgumentException("repositoryName cannot be empty");
        }
        this.repositoryName = repositoryName;
    }
}
