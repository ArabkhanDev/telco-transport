package com.company.telcotransport.repository;


import com.company.telcotransport.entity.NodeRelation;
import com.company.telcotransport.entity.Nodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRelationRepository extends JpaRepository<NodeRelation, Long> {
}
