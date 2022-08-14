package pariwisata.model.admin;

public class Admin {
    
    private Long id;
    private String nama;
    private String user;
    private String password;
    private String role;
    public static String userLogin = "Admin";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", nama=" + nama + ", user=" + user + ", password=" + password + ", role=" + role + '}';
    }
    
}
