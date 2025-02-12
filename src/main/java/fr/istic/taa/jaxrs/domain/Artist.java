package Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist extends User {

    private List<Passage> passages = new ArrayList<Passage>();

    public Artist() {
        super();
    }

    public Artist(String firstname, String lastname, String email, String sexe, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }
}
