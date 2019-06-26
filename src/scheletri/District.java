package scheletri;

import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class District {
	
	private int id;
	private LatLng coord;
	private List<Event> list;

	public District(int id, double d, double e) {
		super();
		this.id = id;
		this.coord = new LatLng(d,e);
		this.list = new ArrayList<>();
	}
	
	public LatLng getCoord() {
		return coord;
	}
	
	public int getId() {
		return id;
	}

	public List<Event> getList() {
		return list;
	}
	
	public void newList() {
		this.list = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		District other = (District) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ID: "+id;
	}	

}
