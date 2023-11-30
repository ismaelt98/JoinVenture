import React, { useRef, useEffect } from 'react';
import './InteractiveMap.css'; // Asegúrate de que este archivo exista y contenga los estilos necesarios
import worldMap from '../../assets/world-map.jpg'; // Asegúrate de que la ruta sea correcta

const InteractiveMap = () => {
  const mapRef = useRef(null);

  useEffect(() => {
    const map = mapRef.current;

    const handleMouseMove = (event) => {
      const { clientX, clientY } = event;
      const { left, top, width, height } = map.getBoundingClientRect();
      const x = (clientX - left - width / 2) / width;
      const y = (clientY - top - height / 2) / height;

      // Ajusta el multiplicador para cambiar la intensidad del efecto
      const xOffset = x * 10; // Multiplicador para el efecto en el eje X
      const yOffset = y * 10; // Multiplicador para el efecto en el eje Y

      // Transforma el estilo basado en la posición del ratón
      map.style.transform = `translate(${xOffset}px, ${yOffset}px)`;
    };

    map.addEventListener('mousemove', handleMouseMove);

    return () => {
      // Limpieza del evento para evitar problemas de memoria
      map.removeEventListener('mousemove', handleMouseMove);
    };
  }, []);

  return (
    <div className="interactive-container">
      <div ref={mapRef} className="interactive-map">
        <img src={worldMap} alt="Mapa del Mundo" />
      </div>
    </div>
  );
};

export default InteractiveMap;
