package com.renaldo.pojo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComboDish is a Querydsl query type for ComboDish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComboDish extends EntityPathBase<ComboDish> {

    private static final long serialVersionUID = 1307451336L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComboDish comboDish = new QComboDish("comboDish");

    public final QCombo combo;

    public final NumberPath<Integer> copies = createNumber("copies", Integer.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> dateCreated = createDateTime("dateCreated", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateModified = createDateTime("dateModified", java.util.Date.class);

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public QComboDish(String variable) {
        this(ComboDish.class, forVariable(variable), INITS);
    }

    public QComboDish(Path<? extends ComboDish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComboDish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComboDish(PathMetadata metadata, PathInits inits) {
        this(ComboDish.class, metadata, inits);
    }

    public QComboDish(Class<? extends ComboDish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.combo = inits.isInitialized("combo") ? new QCombo(forProperty("combo"), inits.get("combo")) : null;
    }

}

