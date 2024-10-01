package han.aim.se.noyoumaynot.movie.user;

public class Role {
    public String name;
    public boolean admin;

    public Role(String name, boolean admin) {
        this.name = name;
        this.admin = admin;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
