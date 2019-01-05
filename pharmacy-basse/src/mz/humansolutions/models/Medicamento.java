package mz.humansolutions.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private double precoUnitario;

	@Column(name = "quantidadeStock", nullable = false, unique = false)
	private int quantidadeStock;

	@Column(nullable = false, columnDefinition = "bit")
	private Boolean active = true;

	@OneToMany(mappedBy="medicamento")
	private List<Movimento> movimento = new ArrayList<>();


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

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
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
