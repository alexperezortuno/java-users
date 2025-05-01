# Security Policy

## Supported Versions
Only the latest version of **java-users** receives security updates. Please ensure you’re using the most recent release.

| Version | Supported          |
| ------- | ------------------ |
| Latest  | ✅ Yes             |
| Older   | ❌ No (unmaintained) |

## Reporting a Vulnerability

**Do not open public GitHub issues for security vulnerabilities!**  
Instead, please:  
1. Email the maintainer directly at **[your-email@example.com]** (or contact [@alexperezortuno](https://github.com/alexperezortuno) privately).  
2. Describe the vulnerability with:  
   - Steps to reproduce.  
   - Expected vs. actual behavior.  
   - Any relevant logs/screenshots.  

We will respond within **7 days** and provide a timeline for fixes.  

## Security Practices

### Data Protection  
- Passwords are hashed (using **[BCrypt/Argon2]**) before storage.  
- No sensitive data (e.g., raw passwords) is logged.  

### Dependencies  
- Regularly updated via **[Dependabot/Maven]** (see `pom.xml`).  
- No known vulnerabilities (checked via **[OWASP Dependency-Check/Snyk]**).  

### Secure Development  
- Input validation enforced for all user-provided data.  
- Automated tests cover security-critical paths (e.g., authentication).  

## Acknowledgements  
We credit responsible reporters (unless requested otherwise).  

---
