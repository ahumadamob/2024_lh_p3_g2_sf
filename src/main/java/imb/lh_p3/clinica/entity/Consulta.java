package imb.lh_p3.clinica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

	@Entity
	public class Consulta {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		private String turno;
		private String diagnostico;
		private String tratamiento;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTurno() {
			return turno;
		}
		public void setTurno(String turno) {
			this.turno = turno;
		}
		public String getDiagnostico() {
			return diagnostico;
		}
		public void setDiagnostico(String diagnostico) {
			this.diagnostico = diagnostico;
		}
		public String getTratamiento() {
			return tratamiento;
		}
		public void setTratamiento(String tratamiento) {
			this.tratamiento = tratamiento;
		}


	}


