import React from "react";

interface InputTextProps {
    style?: string;
    onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
    placeholder?: string;
}

// COMPONENT PARA PADRONIZAÇÃO DE INPUTS - STYLE
export const InputText : React.FC<InputTextProps> = ({ style, onChange, placeholder } : InputTextProps) => {
    return (
       <input type='text' 
            onChange={onChange} // pega o valor digitado no campo input, e seta na query
            placeholder={placeholder}
            className={`${style} border px-5 py-2 rounded-md text-gray-900`}/>
    )
}