'use client'

import { Template, ImageCard } from '@/components'
import { Image } from '@/resources/image/image.resource'
import { useImageService } from '@/resources/image/image.service'
import { useState } from 'react'

export default function GaleriaPage(){

    // Instanciando a classe service de images para utilizar as functions
    const useService = useImageService();
    const [images, setImages] = useState<Image[]>([])
    const [query, setQuery] = useState<string>(' ')          // parametro query da pesquisa
    const [extension, setExtension] = useState<string>(' ')  // parametro extension da pesquisa
    const [loading, setLoading] = useState<boolean>(false)   // parametro de estado de loading da página


    // Função de buscar as imagens no backend 
    async function searchImages(){
        setLoading(true)
        const result = await useService.buscar(query, extension)
        setImages(result)
        setLoading(false)
    }

    // Criar uma ImageCard, a partir do objeto Image
    function renderImageCard(image: Image){
        return (
            <ImageCard nome={image.name} src={image.url} tamanho={image.size} extension={image.extension} dataUpload={image.uploadDate} /> 
        )
    }

    // Cria todos os imageCards baseado no array de imagens do banco
    function renderImageCards(){
        return images.map(renderImageCard) 
    }

    return (
        <Template loading={loading}>
            <section className='flex flex-col items-center justify-center my-3'>
                <div className='flex space-x-4'>
                    <input type='text' 
                        onChange={event => setQuery(event.target.value)} // pega o valor digitado no campo input, e seta na query
                        className='border px-5 py-2 rounded-md text-gray-900'/>
                        
                    <select onChange={event => setExtension(event.target.value)} 
                        className='border px-4 py-2 rounded-md text-gray-900'>
                        <option value=''>All format</option>
                        <option value='PNG'>PNG</option>
                        <option value='JPEG'>JPEG</option>
                        <option value='GIF'>GIF</option>
                    </select>
                    
                    <button className='bg-blue-500 hover:bg-blue-300 text-white px-4 py-2 rounded-md' onClick={searchImages}>Search</button>
                    <button className='bg-yellow-500 hover:bg-yellow-300 text-white px-4 py-2 rounded-md '>Add new</button>
                </div>
            </section>
    
            <section className='grid grid-cols-4 gap-8 my-10'>
                
                {renderImageCards()}

            </section>
        </Template>
    )
}