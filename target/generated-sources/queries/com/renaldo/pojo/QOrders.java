package com.renaldo.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = -1015009435L;

    public static final QOrders orders = new QOrders("orders");

    public final StringPath address = createString("address");

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final StringPath customerName = createString("customerName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.util.Date> orderTime = createDateTime("orderTime", java.util.Date.class);

    public final NumberPath<Integer> payMethod = createNumber("payMethod", Integer.class);

    public final StringPath phone = createString("phone");

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QOrders(String variable) {
        super(Orders.class, forVariable(variable));
    }

    public QOrders(Path<? extends Orders> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrders(PathMetadata metadata) {
        super(Orders.class, metadata);
    }

}

