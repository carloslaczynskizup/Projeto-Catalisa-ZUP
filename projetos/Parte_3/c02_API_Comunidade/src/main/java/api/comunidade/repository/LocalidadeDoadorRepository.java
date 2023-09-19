package api.comunidade.repository;

import api.comunidade.entity.LocalidadeDoadorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalidadeDoadorRepository extends JpaRepository<LocalidadeDoadorModel, Long> {
}
