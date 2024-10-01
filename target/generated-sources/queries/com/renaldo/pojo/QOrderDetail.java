package com.renaldo.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderDetail is a Querydsl query type for OrderDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderDetail extends EntityPathBase<OrderDetail> {

    private static final long serialVersionUID = 36723903L;

    public static final QOrderDetail orderDetail = new QOrderDetail("orderDetail");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final NumberPath<Long> comboId = createNumber("comboId", Long.class);

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final StringPath dishOption = createString("dishOption");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public QOrderDetail(String variable) {
        super(OrderDetail.class, forVariable(variable));
    }

    public QOrderDetail(Path<? extends OrderDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderDetail(PathMetadata metadata) {
        super(OrderDetail.class, metadata);
    }

}

