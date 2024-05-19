package com.company.telcotransport.repository;


import com.company.telcotransport.entity.NodeRelation;
import com.company.telcotransport.entity.Nodes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NodesRepository extends JpaRepository<Nodes, Long> {

    @Query("SELECT n, nr.child FROM Nodes n LEFT JOIN n.childRelations nr WHERE n.id = :id")
    Optional<Nodes> getNodeById(@Param("id") Long id);

    @Query("SELECT COUNT(DISTINCT n.id) " +
            "FROM Nodes n " +
            "LEFT JOIN n.childRelations nc " +
            "WHERE n.id = :parentId OR nc.parent.id = :parentId")
    int countAllRelatedNodes(Long parentId);

    @Transactional
    @Query("SELECT nr FROM NodeRelation nr WHERE nr.parent.id = :parentId")
    List<NodeRelation> findNodeRelationsByParentId(Long parentId);

}
