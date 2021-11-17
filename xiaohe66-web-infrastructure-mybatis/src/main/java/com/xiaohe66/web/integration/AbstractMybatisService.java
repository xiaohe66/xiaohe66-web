package com.xiaohe66.web.integration;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.Id;
import com.xiaohe66.web.integration.domain.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaohe
 * @since 2021.08.12 10:03
 */
public abstract class AbstractMybatisService<C extends DoConverter<A, D>, M extends IBaseMapper<D>, D extends IDo, A extends Aggregate<A, I>, I extends Id>
        extends ServiceImpl<M, D>
        implements IBaseService<D>, Repository<A, I> {

    @Autowired
    protected C dataConverter;

    /**
     * note : 对查询集合时快照保存的思考
     * <p>
     * 1.若保存大量数据，则服务器无法承受，因此限制快照只能保存一个对象。
     * 2.在不提供集合查询的前提下，调用方依然可以保存多个对象的引用。因此限制集合查询没有意义。
     * <p>
     * 因此，对于集合查询制定的规则便是：不保存集合查询的快照。
     */
    protected final AggregateSnapshot<A, I> aggregateSnapshot = new ThreadLocalAggregateSnapshot<>();

    protected void saveSnapshot(A agg) {
        aggregateSnapshot.save(agg);
    }

    protected void removeSnapshot() {
        aggregateSnapshot.remove();
    }

    protected void getSnapshot(I id) {
        aggregateSnapshot.get(id);
    }

    public boolean isExistId(long id) {

        QueryWrapper<D> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("id", id);

        return baseMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void save(A agg) {

        A snapshot = aggregateSnapshot.get(agg.getId());

        if (agg.isSameIdentity(snapshot)) {

            updateImpl(agg, snapshot);

        } else {

            insertImpl(agg);
        }

        snapshot = dataConverter.copyAgg(agg);
        aggregateSnapshot.save(snapshot);
    }

    @Override
    public A getById(I id) {

        A agg = getByIdImpl(id);

        if (agg != null) {

            A snapshot = dataConverter.copyAgg(agg);
            aggregateSnapshot.save(snapshot);
        }

        return agg;
    }

    @Override
    public void removeById(I id) {
        removeByIdImpl(id);
        aggregateSnapshot.remove();
    }

    protected void insertImpl(A agg) {
        save(agg);
    }

    protected void removeByIdImpl(I id) {
        removeById(id.getValue());
    }

    protected void updateImpl(A agg, A snapshot) {
        if (agg.hasDiffRoot(snapshot)) {
            D d = dataConverter.toDo(agg);
            updateById(d);
        }
    }

    protected A getByIdImpl(I id) {
        D d = getById(id.getValue());
        return dataConverter.toAgg(d);
    }
}
