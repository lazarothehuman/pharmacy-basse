package mz.humansolutions.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Medicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, unique = false)
	private String nome;
	
	@Column(nullable = false, unique = true)
	private String codigo;

	@Column(name = "fabricante", nullable = false, unique = false)
	private String fabricante;

	@Column(name = "paisOrigem", nullable = false, unique = false)
	private String paisOrigem;

	@Column(name = "precoUnitario", nullable = false, unique = false)
	private Double precoUnitario;

	@Column(name = "quantidadeStock", nullable = false, unique = false)
	private int quantidadeStock;
	
	@Column(name = "prazo", nullable = false)
	@Type(type = "date")
	private Date prazo;

	@OneToMany(mappedBy="medicamento")
	private List<Movimento> movimentos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;

	@Column(nullable = false, columnDefinition = "bit")
	private Boolean active = true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidadeStock() {
		return quantidadeStock;
	}

	public void setQuantidadeStock(int quantidadeStock) {
		this.quantidadeStock = quantidadeStock;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Movimento> getMovimentos() {
		return movimentos;
	}
	
	public void addMovimento(Movimento movimento) {
		if (movimento != null) 
			this.movimentos.add(movimento);
		
	}
	
	public void addToStock(int quantidade) {
		this.quantidadeStock+=quantidade;
	}

	public void removeFromStock(int quantidade) {
		if(quantidade<this.quantidadeStock)
			this.quantidadeStock-=quantidade;
		
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return nome;
	}





}
