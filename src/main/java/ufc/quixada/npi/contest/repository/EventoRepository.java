package ufc.quixada.npi.contest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ufc.quixada.npi.contest.model.EstadoEvento;
import ufc.quixada.npi.contest.model.Evento;
import ufc.quixada.npi.contest.model.Pessoa;
import ufc.quixada.npi.contest.model.VisibilidadeEvento;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Query("SELECT e FROM Evento e ORDER BY e.inicioSubmissao DESC")
	List<Evento> findAll();

	public List<Evento> findEventoByEstadoAndVisibilidade(EstadoEvento estado, VisibilidadeEvento visibilidade);
	
	@Query("select e from Evento e where e.estado = ufc.quixada.npi.contest.model.EstadoEvento.ATIVO and "
			+ "e.visibilidade = ufc.quixada.npi.contest.model.VisibilidadeEvento.PUBLICO "
			+ "ORDER BY prazo_submissao_inicial DESC")
	public List<Evento> findEventosAtivosEPublicos();
	
	/*@Query("SELECT e FROM Evento e " +
	"WHERE e.id NOT in ( SELECT DISTINCT pe.evento.id FROM ParticipacaoEvento pe WHERE :idPessoa = pe.pessoa.id) "
	+ "AND e.visibilidade = ufc.quixada.npi.contest.model.VisibilidadeEvento.PUBLICO "
	+ "AND e.estado = ufc.quixada.npi.contest.model.EstadoEvento.ATIVO "+
	"ORDER BY e.id")
	public List<Evento> eventosParaParticipar(@Param("idPessoa") Long idPessoa);*/
	
	/*@Query("SELECT DISTINCT e FROM Evento e, ParticipacaoEvento pe " +
			"WHERE pe.pessoa.id = :idPessoa "+ 
			"AND e.id  = pe.evento.id " +
			"AND e.estado = :estado " +
			"AND pe.papel = ufc.quixada.npi.contest.model.Papel$Tipo.ORGANIZADOR " +
			"ORDER BY e.prazoSubmissaoInicial DESC")
	public List<Evento> eventosComoOrganizadorEstado(@Param("idPessoa") Long idPessoa, @Param("estado") EstadoEvento estado);*/
		
	/*@Query("SELECT DISTINCT e FROM Evento e, ParticipacaoEvento pe, ParticipacaoTrabalho pt, Trabalho t " +
			"WHERE pe.pessoa.id = :idPessoa "+ 
			"AND e.id  = pe.evento.id " +
			"AND e.estado = :estado " +
			"AND pe.pessoa.id = pt.pessoa.id " +
			"AND pt.papel = :papel " +
			"AND t.id = pt.trabalho.id " +
			"AND t.evento.id = e.id " +
			"ORDER BY e.prazoSubmissaoInicial DESC")
	public List<Evento> eventosPorPapelEstado(@Param("idPessoa") Long idPessoa, @Param("papel") Tipo papel, @Param("estado") EstadoEvento estado);
	
	@Query("SELECT DISTINCT e FROM Evento e, ParticipacaoEvento pe, ParticipacaoTrabalho pt, Trabalho t " + 
			"WHERE pe.pessoa.id = :idPessoa "+ 
			"AND e.id  = pe.evento.id " +
			"AND pe.pessoa.id = pt.pessoa.id " +
			"AND pt.papel = :papel " +
			"AND t.id = pt.trabalho.id " +
			"AND t.evento.id = e.id " +
			"ORDER BY e.prazoSubmissaoInicial DESC")
	public List<Evento> eventosPorPapel(@Param("idPessoa") Long idPessoa, @Param("papel") Tipo papel);*/
	
/*	public List<Evento> findDistinctEventoByParticipacoesPessoaId(Long id);*/
	
	/*public List<Evento> findDistinctEventoByParticipacoesPessoaIdAndVisibilidade(Long id, VisibilidadeEvento visbilidade);*/
	
	public List<Evento> findEventoByEstado(EstadoEvento estadoEvento);

    @Query("SELECT r FROM Evento e LEFT JOIN e.revisores r WHERE e = :evento AND upper(r.nome) LIKE upper(concat('%', :nome,'%'))")
	List<Pessoa> findRevisores(@Param("evento") Evento evento, @Param("nome") String nome);
	
/*	@Query("select case when count(*) > 0 then true else false end "
			+ "FROM Revisao as r, Trabalho as t  WHERE r.trabalho.id = t.id and t.evento.id = :idEvento")
	public boolean organizadorParticipaEvento(@Param("idEvento") Long idEvento);*/

}