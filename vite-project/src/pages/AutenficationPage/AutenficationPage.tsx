import { useState, useEffect } from 'react';
import style from './autenficationpage.module.css';
import background1 from '../../assets/background1.jpg';
import background2 from '../../assets/background2.jpg';
import background3 from '../../assets/background3.jpg';

function AutenficationPage() {
    const [currentBackground, setCurrentBackground] = useState(0);
    const backgrounds = [background1, background2, background3];
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentBackground((prev) => (prev === 2 ? 0 : prev + 1));
        }, 3000);

        return () => clearInterval(interval);
    }, []);

    return (
        <div className={style.background} style={{ backgroundImage: `url(${backgrounds[currentBackground]})` }}>
            {/* Contenido de tu componente */}
        </div>
    );
}

export default AutenficationPage;