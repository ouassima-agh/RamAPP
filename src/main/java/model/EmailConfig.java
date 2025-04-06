package model;



import jakarta.persistence.*;

@Entity
@Table(name = "email_configuration")
public class EmailConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_email_config")
    private Long id;

    @Column(nullable = false)
    private String smtpServer;

    @Column(nullable = false)
    private int smtpPort;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean sslEnabled;

    @Column(name = "from_email", nullable = false)
    private String fromEmail;

    @Column(name = "from_name", nullable = false)
    private String fromName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public EmailConfig() {
    }

    public EmailConfig(Long id, String smtpServer, int smtpPort, String username, String password, boolean sslEnabled, String fromEmail, String fromName) {
        this.id = id;
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.sslEnabled = sslEnabled;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }
}