package api.comunidade.repository;

import api.comunidade.entity.AlimentosDoadosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentosDoadosRepository extends JpaRepository<AlimentosDoadosModel, Long> {
}
