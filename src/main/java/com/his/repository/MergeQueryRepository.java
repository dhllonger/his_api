package com.his.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.his.pojo.dto.PatientToDiagnoseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.his.pojo.PatiOutVisit;
import java.util.List;

@Repository
public interface MergeQueryRepository extends CrudRepository<PatiOutVisit, String> {

	@Query("SELECT new com.his.pojo.dto.PatientToDiagnoseDto(p.pid, p.pname, p.pidcard, p.ptel, v.rid, v.rtype, v.dpmtnNme, v.doctName, v.registDate) " +
	           "FROM PatiOutVisit v " +
	           "INNER JOIN PatiInfoBasic p ON v.pid = p.pid " +
	           "WHERE FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-%d') = v.registDate AND v.doctName = :doctName")
	    List<PatientToDiagnoseDto> findPatientToDiagnose(@Param("doctName") String doctName);
	    
	@Query("SELECT new com.his.pojo.dto.PatientToDiagnoseDto(p.pid, p.pname, p.pidcard, p.ptel, v.rid, v.rtype, v.dpmtnNme, v.doctName, v.registDate) " +
	           "FROM PatiOutVisit v " +
	           "INNER JOIN PatiInfoBasic p ON v.pid = p.pid " +
	           "INNER JOIN MedicalRecord r ON v.rid = r.rid " +
	           "WHERE FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-%d') = v.registDate AND v.doctName = :doctName")
	    List<PatientToDiagnoseDto> findPatientToOrder(@Param("doctName") String doctName);
	    
	    
    @Query(value="select p.pid,p.pname,p.pidcard,p.ptel,v.rid,v.rtype,v.dpmtnNme,v.doctName,v.registDate from pati_out_visit v inner join pati_info_basic p on v.pid=p.pid and v.registDate=CURDATE() where doctName=:doctName", nativeQuery = true)
    List<Object[]> findPatientToDiagnose2(@Param("doctName") String doctName);
    
    
    
}
