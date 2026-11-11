/* ==========================================================================
   PURE MONOCHROME ANIMATIONS & INTERACTIVE EFFECTS
   Modern, elegant, high-performance interactions
   ========================================================================== */

(function () {
    'use strict';

    document.addEventListener('DOMContentLoaded', function () {

        /* -------------------------------------------------------------
         * 1. DYNAMIC TYPEWRITER EFFECT IN HERO
         * ------------------------------------------------------------- */
        (function initTypewriter() {
            const roleEl = document.getElementById('typewriter-role');
            if (!roleEl) return;

            const roles = [
                'Full-Stack Developer',
                'Spring Boot & Microservices Specialist',
                'SLIIT ISE Engineering Undergrad',
                'Enterprise Software Craftsman',
                'Database & API Architect'
            ];

            let roleIdx = 0;
            let charIdx = 0;
            let isDeleting = false;
            const typingSpeed = 90;
            const pauseTime = 2000;

            function typeLoop() {
                const currentText = roles[roleIdx];
                if (isDeleting) {
                    roleEl.textContent = currentText.substring(0, charIdx - 1);
                    charIdx--;
                } else {
                    roleEl.textContent = currentText.substring(0, charIdx + 1);
                    charIdx++;
                }

                let nextDelay = isDeleting ? typingSpeed / 2 : typingSpeed;

                if (!isDeleting && charIdx === currentText.length) {
                    nextDelay = pauseTime;
                    isDeleting = true;
                } else if (isDeleting && charIdx === 0) {
                    isDeleting = false;
                    roleIdx = (roleIdx + 1) % roles.length;
                    nextDelay = 500;
                }

                setTimeout(typeLoop, nextDelay);
            }

            typeLoop();
        })();

        /* -------------------------------------------------------------
         * 2. INTERACTIVE SPOTLIGHT GLOW ON CARDS
         * ------------------------------------------------------------- */
        (function initCardSpotlights() {
            const spotlightCards = document.querySelectorAll('.pop-card, .contact-glass-box, .photo-card-main, .contact-item-row');
            spotlightCards.forEach(card => {
                card.addEventListener('mousemove', function (e) {
                    const rect = card.getBoundingClientRect();
                    const x = e.clientX - rect.left;
                    const y = e.clientY - rect.top;
                    card.style.setProperty('--mouse-x', `${x}px`);
                    card.style.setProperty('--mouse-y', `${y}px`);
                });
            });
        })();

        /* -------------------------------------------------------------
         * 3. 3D CARD PERSPECTIVE TILT (.pop-card)
         * ------------------------------------------------------------- */
        (function init3DTilt() {
            const cards = document.querySelectorAll('.pop-card');
            cards.forEach(card => {
                card.addEventListener('mousemove', function (e) {
                    const rect = card.getBoundingClientRect();
                    const x = e.clientX - rect.left;
                    const y = e.clientY - rect.top;
                    const centerX = rect.width / 2;
                    const centerY = rect.height / 2;

                    const rotateX = ((y - centerY) / centerY) * -7;
                    const rotateY = ((x - centerX) / centerX) * 7;

                    card.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) translateY(-8px) scale3d(1.02, 1.02, 1.02)`;
                });

                card.addEventListener('mouseleave', function () {
                    card.style.transform = 'perspective(1000px) rotateX(0deg) rotateY(0deg) translateY(0) scale3d(1, 1, 1)';
                });
            });
        })();

        /* -------------------------------------------------------------
         * 4. SCROLL REVEAL (IntersectionObserver with stagger)
         * ------------------------------------------------------------- */
        (function initScrollReveal() {
            const revealElements = document.querySelectorAll('[data-reveal]');
            if (!revealElements.length) return;

            const observer = new IntersectionObserver((entries, obs) => {
                entries.forEach((entry, index) => {
                    if (entry.isIntersecting) {
                        setTimeout(() => {
                            entry.target.classList.add('revealed');
                        }, index * 70);
                        obs.unobserve(entry.target);
                    }
                });
            }, {
                threshold: 0.1,
                rootMargin: '0px 0px -40px 0px'
            });

            revealElements.forEach(el => observer.observe(el));
        })();

        /* -------------------------------------------------------------
         * 5. ANIMATED NUMBER COUNTERS (easeOutCubic)
         * ------------------------------------------------------------- */
        (function initCounters() {
            const counters = document.querySelectorAll('.counter-val');
            if (!counters.length) return;

            const observer = new IntersectionObserver((entries, obs) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        const el = entry.target;
                        const target = parseInt(el.getAttribute('data-count') || el.innerText, 10);
                        if (isNaN(target)) return;

                        const duration = 1800;
                        let startTime = null;

                        function step(timestamp) {
                            if (!startTime) startTime = timestamp;
                            const progress = Math.min((timestamp - startTime) / duration, 1);
                            const ease = 1 - Math.pow(1 - progress, 3);
                            el.textContent = Math.floor(ease * target);
                            if (progress < 1) {
                                requestAnimationFrame(step);
                            } else {
                                el.textContent = target;
                            }
                        }
                        requestAnimationFrame(step);
                        obs.unobserve(el);
                    }
                });
            }, { threshold: 0.3 });

            counters.forEach(el => observer.observe(el));
        })();

        /* -------------------------------------------------------------
         * 6. MAGNETIC BUTTON HOVER EFFECT
         * ------------------------------------------------------------- */
        (function initMagneticButtons() {
            const btns = document.querySelectorAll('.btn-pop-primary, .btn-pop-secondary');
            btns.forEach(btn => {
                btn.addEventListener('mousemove', function (e) {
                    const rect = btn.getBoundingClientRect();
                    const x = e.clientX - rect.left - rect.width / 2;
                    const y = e.clientY - rect.top - rect.height / 2;
                    btn.style.transform = `translate(${x * 0.22}px, ${y * 0.22}px) scale(1.03)`;
                });

                btn.addEventListener('mouseleave', function () {
                    btn.style.transform = 'translate(0px, 0px) scale(1)';
                    btn.style.transition = 'transform 0.4s ease';
                });

                btn.addEventListener('mouseenter', function () {
                    btn.style.transition = 'transform 0.12s ease';
                });
            });
        })();

        /* -------------------------------------------------------------
         * 7. BUTTON RIPPLE CLICK EFFECT
         * ------------------------------------------------------------- */
        (function initButtonRipples() {
            document.querySelectorAll('.btn-pop-primary, .btn-pop-secondary').forEach(button => {
                button.addEventListener('click', function (e) {
                    const rect = button.getBoundingClientRect();
                    const circle = document.createElement('span');
                    const diameter = Math.max(rect.width, rect.height);
                    const radius = diameter / 2;

                    circle.style.width = circle.style.height = `${diameter}px`;
                    circle.style.left = `${e.clientX - rect.left - radius}px`;
                    circle.style.top = `${e.clientY - rect.top - radius}px`;
                    circle.style.position = 'absolute';
                    circle.style.borderRadius = '50%';
                    circle.style.backgroundColor = button.classList.contains('btn-pop-primary') ? 'rgba(0,0,0,0.2)' : 'rgba(255,255,255,0.3)';
                    circle.style.transform = 'scale(0)';
                    circle.style.animation = 'buttonRipple 0.6s linear';
                    circle.style.pointerEvents = 'none';

                    const ripple = button.getElementsByClassName('btn-ripple')[0];
                    if (ripple) {
                        ripple.remove();
                    }

                    circle.classList.add('btn-ripple');
                    button.appendChild(circle);
                });
            });

            // Inject keyframe once
            if (!document.getElementById('ripple-style')) {
                const style = document.createElement('style');
                style.id = 'ripple-style';
                style.textContent = '@keyframes buttonRipple { to { transform: scale(3.5); opacity: 0; } }';
                document.head.appendChild(style);
            }
        })();

        /* -------------------------------------------------------------
         * 8. BACK TO TOP BUTTON
         * ------------------------------------------------------------- */
        (function initBackToTop() {
            let btn = document.getElementById('back-to-top');
            if (!btn) {
                btn = document.createElement('button');
                btn.id = 'back-to-top';
                btn.innerHTML = '<i class="bi bi-arrow-up"></i>';
                btn.setAttribute('aria-label', 'Back to top');
                document.body.appendChild(btn);
            }

            window.addEventListener('scroll', function () {
                if (window.scrollY > 350) {
                    btn.classList.add('visible');
                } else {
                    btn.classList.remove('visible');
                }
            }, { passive: true });

            btn.addEventListener('click', function () {
                window.scrollTo({ top: 0, behavior: 'smooth' });
            });
        })();

        /* -------------------------------------------------------------
         * 9. MONOCHROME TOAST POPUP NOTIFICATION
         * ------------------------------------------------------------- */
        window.showMonochromeToast = function (message, icon) {
            let container = document.getElementById('toast-container');
            if (!container) {
                container = document.createElement('div');
                container.id = 'toast-container';
                document.body.appendChild(container);
            }

            const toast = document.createElement('div');
            toast.className = 'monochrome-toast';
            const iconHtml = icon ? `<i class="${icon}"></i>` : '<i class="bi bi-check2-circle fs-5 text-white"></i>';
            toast.innerHTML = `${iconHtml}<span>${message}</span>`;

            container.appendChild(toast);

            requestAnimationFrame(() => {
                toast.classList.add('show');
            });

            setTimeout(() => {
                toast.classList.remove('show');
                setTimeout(() => {
                    if (toast.parentNode) toast.parentNode.removeChild(toast);
                }, 400);
            }, 3500);
        };

        /* -------------------------------------------------------------
         * 10. COPY TO CLIPBOARD HELPER
         * ------------------------------------------------------------- */
        window.copyToClipboard = function (text, successMsg) {
            if (navigator.clipboard && window.isSecureContext) {
                navigator.clipboard.writeText(text).then(() => {
                    window.showMonochromeToast(successMsg || 'Copied to clipboard! 📋');
                }).catch(() => {
                    fallbackCopy(text, successMsg);
                });
            } else {
                fallbackCopy(text, successMsg);
            }
        };

        function fallbackCopy(text, successMsg) {
            const textArea = document.createElement('textarea');
            textArea.value = text;
            textArea.style.position = 'fixed';
            textArea.style.left = '-9999px';
            document.body.appendChild(textArea);
            textArea.focus();
            textArea.select();
            try {
                document.execCommand('copy');
                window.showMonochromeToast(successMsg || 'Copied to clipboard! 📋');
            } catch (err) {
                window.showMonochromeToast('Could not copy', 'bi bi-x-circle');
            }
            document.body.removeChild(textArea);
        }

        /* -------------------------------------------------------------
         * 11. SMOOTH ANCHOR NAVIGATION WITH OFFSET
         * ------------------------------------------------------------- */
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                const targetId = this.getAttribute('href');
                if (targetId === '#' || targetId === '') return;
                const targetElement = document.querySelector(targetId);
                if (targetElement) {
                    e.preventDefault();
                    targetElement.scrollIntoView({ behavior: 'smooth' });
                }
            });
        });

    });
})();
