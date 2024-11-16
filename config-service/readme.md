Access a Configuration File: Use the following URL format to fetch configuration files:

css
Copier le code
http://localhost:8888/{application}/{profile}/{label}
{application}: The name of the application (e.g., my-service).
{profile}: The profile (e.g., default, dev, prod).
{label}: The Git branch or tag (optional, defaults to main or as configured).