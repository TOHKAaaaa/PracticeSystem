package cn.edu.zucc.practiceSystem.DAO;

import cn.edu.zucc.practiceSystem.entity.StudentFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentFormDao extends JpaRepository<StudentFormEntity,Long>, JpaSpecificationExecutor<StudentFormEntity> {

}
