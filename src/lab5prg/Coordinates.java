package lab5prg;

import com.fasterxml.jackson.annotation.JsonProperty;

import exceptions.*;

public class Coordinates implements Comparable<Coordinates> {
	@JsonProperty("x")
	private Long x;
	@JsonProperty("y")
	private double y;
	public Coordinates() {
		
	}
	public Coordinates(Long x, double y) throws NotNullException, MustBeLessThanException{
		if (x!=null)  {
			this.x=x;
		} else {
			throw new NotNullException("x");
		}
		if (y <= (double) 882) {
			this.y=y;
		} else {
			throw new MustBeLessThanException("y", 882D);
		}
	}
	public Long getX() {
		return x;
	}
	public void setX(Long x) throws NotNullException {
		if (x!=null)  {
			this.x=x;
		} else {
			throw new NotNullException("x");
		}
	}
	public double getY() {
		return y;
	}
	public void setY(double y) throws MustBeLessThanException {
		if (y <= (double) 882) {
			this.y=y;
		} else {
			throw new MustBeLessThanException("y", 882D);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o!=null && o instanceof Coordinates) {
			if (this.x.equals(((Coordinates) o).getX()) &&
					this.y==((Coordinates) o).getY()) {
				return true;
			} else {
				return false;
			}
 		} else {
			return false;
		}
		
	}
	@Override
	public int compareTo(Coordinates o) {
		if (this.equals(o)) {
			return 0;
		} else {
			if (Math.hypot( this.x.doubleValue(), this.y ) - Math.hypot(o.getX().doubleValue(), o.getY()) > 0) {
				return 1;
			} else {
				return -1;
			}
			}
		}
	}
