package data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Index
public class GroupsEp {
    @Id
    private Long id;
    private int id_group;
    private String name_group;
    private String adress_group;
    private byte[] photo_profile_group;
    private int type_group;

    public GroupsEp() {
    }

    public GroupsEp(int id_group, String name_group, String adress_group, byte[] photo_profile_group, int type_group) {
        this.id_group = id_group;
        this.name_group = name_group;
        this.adress_group = adress_group;
        this.photo_profile_group = photo_profile_group;
        this.type_group = type_group;
    }

    public GroupsEp(int id_group, String name_group, String adress_group, int type_group) {
        this.id_group = id_group;
        this.name_group = name_group;
        this.adress_group = adress_group;
        this.type_group = type_group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public String getAdress_group() {
        return adress_group;
    }

    public void setAdress_group(String adress_group) {
        this.adress_group = adress_group;
    }

    public byte[] getPhoto_profile_group() {
        return photo_profile_group;
    }

    public void setPhoto_profile_group(byte[] photo_profile_group) {
        this.photo_profile_group = photo_profile_group;
    }

    public int getType_group() {
        return type_group;
    }

    public void setType_group(int type_group) {
        this.type_group = type_group;
    }
}
