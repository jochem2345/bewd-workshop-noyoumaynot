package han.aim.se.noyoumaynot.movie.user;

public class Role {
    public String rolename;
    public boolean admin;

    public Role(String rolename, boolean admin) {
        this.rolename = rolename;
        this.admin = admin;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
