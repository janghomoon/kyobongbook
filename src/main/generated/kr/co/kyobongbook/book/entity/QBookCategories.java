package kr.co.kyobongbook.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookCategories is a Querydsl query type for BookCategories
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookCategories extends EntityPathBase<BookCategories> {

    private static final long serialVersionUID = -2022368156L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookCategories bookCategories = new QBookCategories("bookCategories");

    public final kr.co.kyobongbook.common.entity.QBaseEntity _super = new kr.co.kyobongbook.common.entity.QBaseEntity(this);

    public final QBooks book;

    public final kr.co.kyobongbook.book.entity.id.QBookCategoryId bookCategoryId;

    public final QCategories category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBookCategories(String variable) {
        this(BookCategories.class, forVariable(variable), INITS);
    }

    public QBookCategories(Path<? extends BookCategories> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookCategories(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookCategories(PathMetadata metadata, PathInits inits) {
        this(BookCategories.class, metadata, inits);
    }

    public QBookCategories(Class<? extends BookCategories> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBooks(forProperty("book")) : null;
        this.bookCategoryId = inits.isInitialized("bookCategoryId") ? new kr.co.kyobongbook.book.entity.id.QBookCategoryId(forProperty("bookCategoryId")) : null;
        this.category = inits.isInitialized("category") ? new QCategories(forProperty("category")) : null;
    }

}

