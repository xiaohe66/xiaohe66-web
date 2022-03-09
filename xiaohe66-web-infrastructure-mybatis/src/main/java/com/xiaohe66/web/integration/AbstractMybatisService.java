package com.xiaohe66.web.integration;

import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.integration.domain.Id;
import com.xiaohe66.web.integration.domain.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author xiaohe
 * @since 2021.08.12 10:03
 */
public abstract class AbstractMybatisService<C extends DoConverter<A, D>, M extends MapperSupport<D>, D extends IDo, A extends Aggregate<A, I>, I extends Id>
        extends ServiceSupport<M, D>
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

    protected final void saveSnapshot(A agg) {
        aggregateSnapshot.save(agg);
    }

    protected final void removeSnapshot() {
        aggregateSnapshot.remove();
    }

    protected final void getSnapshot(I id) {
        aggregateSnapshot.get(id);
    }

    public boolean isExistId(Serializable id) {
        return baseMapper.isExistId(id);
    }

    @Override
    public boolean isExist(I id) {
        return baseMapper.isExistId(id.getValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(A agg) {

        A snapshot = aggregateSnapshot.get(agg.getId());

        if (agg.isSameIdentity(snapshot)) {

            updateImpl(agg, snapshot);

        } else {

            // 若在更新前未执行查询操作，若多个快照之间相互覆盖，则无法使用快照判断是否存在。这时就需要判断数据库中是否存在
            boolean existId = isExistId(agg.getId().getValue());
            if (existId) {
                updateImpl(agg, snapshot);

            } else {
                insertImpl(agg);
            }
        }

        // 保存快照
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeById(I id) {
        removeByIdImpl(id);
        aggregateSnapshot.remove();
    }

    protected void insertImpl(A agg) {
        D d = dataConverter.toDo(agg);
        save(d);
    }

    protected void removeByIdImpl(I id) {
        removeById(id.getValue());
    }

    /**
     * 真实的 update 方法实现
     * <p>
     * note : 仅更新数据，不处理快照，快照由调用方处理
     */
    protected void updateImpl(A agg, A snapshot) {
        if (agg.hasDiffRootAttribute(snapshot)) {
            D d = dataConverter.toDo(agg);
            updateById(d);
        }
    }

    protected A getByIdImpl(I id) {
        D d = getById(id.getValue());
        return dataConverter.toAgg(d);
    }
}
