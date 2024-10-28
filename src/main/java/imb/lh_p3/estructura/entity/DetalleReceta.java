package imb.lh_p3.estructura.entity;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	@Entity
	public class DetalleReceta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	    private String receta;
	    private String medicamento;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getReceta() {
			return receta;
		}
		public void setReceta(String receta) {
			this.receta = receta;
		}
		public String getMedicamento() {
			return medicamento;
		}
		public void setMedicamento(String medicamento) {
			this.medicamento = medicamento;
		}
}
