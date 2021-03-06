package ufc.quixada.npi.contest.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="secao")
public class Sessao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "local")
	private String local;
	@Column(name="data_secao")
	private String dataSecao;
	@Column(name="horario")
	private String horario;
	
	@OneToMany(mappedBy = "sessao")
	private List<Trabalho> trabalhos;
	
	@ManyToOne
	@JoinColumn(name="responsavel_id")
	private Pessoa responsavel;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Trabalho> getTrabalhos() {
		return trabalhos;
	}

	public void setTrabalhos(List<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getDataSecao() {
		return dataSecao;
	}

	public void setDataSecao(String dataSecao) {
		this.dataSecao = dataSecao;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void update(Sessao sessao) {
		this.nome = sessao.getNome();
		this.responsavel = sessao.getResponsavel();
		this.local = sessao.getLocal();
		this.dataSecao = sessao.getDataSecao();
		this.horario = sessao.getHorario();
	}

	public boolean isReponsavel(Object object) {
		Pessoa pessoa = (Pessoa) object;
		return this.responsavel.equals(pessoa);
	}
}
