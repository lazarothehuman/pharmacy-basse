package mz.humansolutions.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Movimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo", nullable = false, unique = false)
	private String tipo;

	@Column(name = "data", nullable = false)
	@Type(type = "date")
	private Date data;

	@Column(name = "data_validade", nullable = false)
	@Type(type = "date")
	private Date data_validade;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Medicamento medicamento;

	@Column(name = "quantidade", nullable = false, unique = false)
	private int quantidade;

	@Column(name = "id_fornecedor", nullable = false, unique = false)
	private int id_fornecedor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User registador;

	@Column(name = "idCliente",  unique = false)
	private int idCliente;

	@Column(nullable = false, columnDefinition = "bit")
	private Boolean active = true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date localDate) {
		this.data = localDate;
	}

	public Date getData_validade() {
		return data_validade;
	}

	public void setData_validade(Date localDate) {
		this.data_validade = localDate;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(int id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public User getRegistador() {
		return registador;
	}

	public void setRegistador(User registador) {
		this.registador = registador;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	

}
