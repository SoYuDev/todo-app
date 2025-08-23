package com.luis.todoapp.task.model;

import com.luis.todoapp.category.model.Category;
import com.luis.todoapp.tag.model.Tag;
import com.luis.todoapp.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob // The String will be stored as a CLOB (Character Large Object) used for large fields like descriptions.
    private String description;

    private boolean completed;

    @Builder.Default // Sets 'created' to the current time if not provided in the Builder.
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne // Indicates many-to-one relationship between two entities.
    // @JoinColumn creates a column (by default named category_id) that will store the ID of the category
    // foreignKey = @ForeignKey(name = "fk_task_category") names the foreign key constraint to fk_task_category
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_task_category"))
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER) // When we load a Task, also loads its tags immediately, alternative is LAZY which loads tags only when accessed.
    // Many-to-many ALWAYS needs a join table, this table connects Task and Tag.
    @JoinTable(name = "task_tag",
            joinColumns = @JoinColumn(name = "task_id"),
            foreignKey = @ForeignKey(name = "fk_task_tag_task"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            inverseForeignKey = @ForeignKey(name = "fk_task_tag_tag")
    )
    @Builder.Default
    @Setter(AccessLevel.NONE) // Eliminates the setter of this field or attribute.
    private Set<Tag> tags = new HashSet<>(); // We use a set instead of a list to avoid duplicates.

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_task_user"))
    private User author;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
