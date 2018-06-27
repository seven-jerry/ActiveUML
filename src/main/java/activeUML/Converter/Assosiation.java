package activeUML.Converter;

public class Assosiation {
	private String componentString;
	private AssociationType type;
	private String source;
	public Assosiation(){
	}
	public String getComponentString() {
		return componentString;
	}
	public void setComponentString(String componentString) {
		this.componentString = componentString;
	}
	public AssociationType getType() {
		return type;
	}
	public void setType(AssociationType type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((componentString == null) ? 0 : componentString.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assosiation other = (Assosiation) obj;
		if (componentString == null) {
			if (other.componentString != null)
				return false;
		} else if (!componentString.equals(other.componentString))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
