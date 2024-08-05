package kr.co.kyobongbook.book.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooks is a Querydsl query type for Books
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooks extends EntityPathBase<Books> {

    private static final long serialVersionUID = 1172470091L;

    public static final QBooks books = new QBooks("books");

    public final kr.co.kyobongbook.common.entity.QBaseEntity _super = new kr.co.kyobongbook.common.entity.QBaseEntity(this);

    public final StringPath author = createString("author");

    public final ListPath<BookCategories, QBookCategories> bookCategories = this.<BookCategories, QBookCategories>createList("bookCategories", BookCategories.class, QBookCategories.class, PathInits.DIRECT2);

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath isAvailable = createBoolean("isAvailable");

    public final StringPath notAvailableReason = createString("notAvailableReason");

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBooks(String variable) {
        super(Books.class, forVariable(variable));
    }

    public QBooks(Path<? extends Books> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBooks(PathMetadata metadata) {
        super(Books.class, metadata);
    }

}

