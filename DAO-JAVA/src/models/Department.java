package models;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
  private static final long serialVerionUID = 1l;

  private Integer id;
  private String name;

  public Department(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Department() {
  }

  public long getSerialVerionUID() {
    return serialVerionUID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Department{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Department)) return false;
    Department that = (Department) o;
    return Objects.equals(getId(), that.getId()) && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
