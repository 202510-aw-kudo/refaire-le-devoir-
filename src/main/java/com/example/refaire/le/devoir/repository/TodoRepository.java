package com.example.refaire.le.devoir.repository;

import com.example.refaire.le.devoir.entity.Todo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

  List<Todo> findByCompleted(Boolean completed);

  List<Todo> findByTitleContainingIgnoreCase(String keyword);

  List<Todo> findByDueDateLessThanEqual(LocalDate date);

  default List<Todo> findAllOrderByPriorityAsc() {
    return findAll(Sort.by(Sort.Direction.ASC, "priority"));
  }

  @Query("select t from Todo t where t.completed = :completed and t.title like %:keyword%")
  List<Todo> searchByCompletedAndTitle(
      @Param("completed") Boolean completed,
      @Param("keyword") String keyword
  );
}
