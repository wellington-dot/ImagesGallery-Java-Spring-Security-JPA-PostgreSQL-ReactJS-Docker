import { Image } from './image.resource'

class ImageService {
    baseUrl: string = 'http://localhost:8080/v1/images';

    // Recebe um array de imagens, quando não especificamos a operação no fetch, ele utiliza o Get 
    async buscar(query: string = "", extension: string = "") : Promise<Image[]> {
        const url = `${this.baseUrl}?query=${query}&extension=${extension}`
        const response = await fetch(url);
        return await response.json();
    }
}


export const useImageService = () => new ImageService();