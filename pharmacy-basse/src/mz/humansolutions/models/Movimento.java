package mz.humansolutions.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Movimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private Tipo tipo;

	@Column(name = "dataRealizacao", nullable = false)
	@Type(type = "date")
	private Date dataRealizacao;

	@ManyToMany
	private List<Medicamento> medicamentos = new ArrayList<>();

	// quando ocorre uma entrada,este dado recebe null
	@ManyToOne
	@JoinColumn(nullable = true)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private User registador;

	@Column(nullable = false, columnDefinition = "bit")
	private Boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date localDate) {
		this.dataRealizacao = localDate;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamento) {
		this.medicamentos = medicamento;
	}

	public User getRegistador() {
		return registador;
	}

	public void setRegistador(User registador) {
		this.registador = registador;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void addMedicamento(Medicamento medicamento) {
		if (medicamento != null)
			this.medicamentos.add(medicamento);
	}
}
