package io.smsc.repository.admin;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import io.smsc.model.admin.QUser;
import io.smsc.model.admin.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This REST repository class is used for providing default {@link JpaRepository}
 * CRUD methods to operate with {@link User} entities and exporting them to
 * appropriate endpoints.
 *
 * @author Nazar Lipkovskyy
 * @since 0.0.1-SNAPSHOT
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Transactional(readOnly = true)
@Repository("AdminUserRepository")
public interface UserRepository extends JpaRepository<User, Long>,
        QueryDslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {

    @Override
    default public void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.password);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long id);

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User save(User user);

    @Override
    @EntityGraph(attributePaths = {"dashboards", "roles", "authorities"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    User findOne(Long id);

    @EntityGraph(attributePaths = {"dashboards", "roles", "authorities"})
    User findByUsername(@Param("username") String userName);

    @EntityGraph(attributePaths = {"dashboards", "roles", "authorities"})
    User findByEmail(@Param("email") String email);

    @Override
    @EntityGraph(attributePaths = {"dashboards", "roles", "authorities"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"dashboards", "roles", "authorities"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAll(Predicate predicate, Pageable pageable);

    @PreAuthorize("isAuthenticated()")
    @Query("select u from AdminUser u where u.id = ?#{ principal?.id }")
    User me();
}