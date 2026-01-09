import React from "react";

interface ButtonProps {
    color?: string;
    label?: string;
    onClick?: (event: any) => void;
}

// COMPONENT PARA PADRONIZAÇÃO DE BOTÕES - STYLE
export const Button: React.FC<ButtonProps> = ({ onClick, color, label } : ButtonProps) => {
    return (
        <>
            <button className={`${color} text-white px-4 py-2 rounded-md`} 
                onClick={ onClick }>
                { label }
            </button>

        </>
    )
}