// Theme Management
(function() {
    const THEME_KEY = 'portfolio-theme';
    const DEFAULT_THEME = 'default';

    function getTheme() {
        return localStorage.getItem(THEME_KEY) || DEFAULT_THEME;
    }

    function setTheme(theme) {
        localStorage.setItem(THEME_KEY, theme);
        applyTheme(theme);
    }

    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        updateThemeButtons(theme);
    }

    function updateThemeButtons(theme) {
        document.querySelectorAll('.theme-btn').forEach(btn => {
            btn.classList.remove('active');
            if (btn.dataset.theme === theme) {
                btn.classList.add('active');
            }
        });
    }

    // Initialize theme on page load
    document.addEventListener('DOMContentLoaded', function() {
        const currentTheme = getTheme();
        applyTheme(currentTheme);

        // Add theme switcher buttons
        const themeSwitcher = document.createElement('div');
        themeSwitcher.className = 'theme-switcher';
        themeSwitcher.innerHTML = `
            <button class="theme-btn" data-theme="light" title="Light Mode">
                <i class="bi bi-sun-fill"></i>
            </button>
            <button class="theme-btn" data-theme="default" title="Default Mode">
                <i class="bi bi-circle-half"></i>
            </button>
            <button class="theme-btn" data-theme="dark" title="Dark Mode">
                <i class="bi bi-moon-stars-fill"></i>
            </button>
        `;
        document.body.appendChild(themeSwitcher);

        // Add click handlers
        document.querySelectorAll('.theme-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                setTheme(this.dataset.theme);
            });
        });

        updateThemeButtons(currentTheme);
    });
})();