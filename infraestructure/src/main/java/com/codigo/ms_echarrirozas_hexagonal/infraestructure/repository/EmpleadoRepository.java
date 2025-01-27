package com.codigo.ms_echarrirozas_hexagonal.infraestructure.repository;

import com.codigo.ms_echarrirozas_hexagonal.infraestructure.entity.EmpleadoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByNumDoc(String numDoc);
    List<EmpleadoEntity> findAllByEstado(boolean estado);
    /*EmpleadoEntity actualizarEmpleado(int edad,
                                      String cargo,
                                      double salario,
                                      String telefono,
                                      String correo,
                                      String departamento,
                                      String direccion);*/
    @Transactional
    @Modifying
    @Query("UPDATE EmpleadoEntity e SET e.edad = :edad, e.salario = :salario, e.cargo = :cargo, e.departamento = :departamento, e.correo = :correo, e.telefono = :telefono, e.direccion = :direccion, e.usuaUpdate= :usuaUpdate, e.dateUpdate= :dateUpdate WHERE e.numDoc = :numDoc")
    void actualizarEmpleado(String numDoc,
                            int edad,
                            String cargo,
                            double salario,
                            String telefono,
                            String correo,
                            String departamento,
                            String direccion,
                            String usuaUpdate,
                            Timestamp dateUpdate
                            );

    @Modifying
    @Transactional
    @Query("UPDATE EmpleadoEntity e SET e.estado = false, e.usuaDelete= :usuario, e.dateDelete= :dateDelete WHERE e.numDoc = :numDoc")
    void eliminadoLogicoEmpleado(String numDoc, String usuario, Timestamp dateDelete);
}
