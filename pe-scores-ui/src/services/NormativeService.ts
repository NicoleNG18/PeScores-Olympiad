import axios from 'axios';

export interface INormative {
    id: number;
    grade: number;
    gender: 'BOY' | 'GIRL';
    discipline: string;
    points: number;
    minResult: number;
    maxResult: number;
}

const API_URL = 'http://localhost:8080/api/normatives';

export class NormativeService {
    public async getDisciplines(): Promise<string[]> {
        return (await axios.get<string[]>(`${API_URL}/disciplines`)).data;
    }

    public async getNormatives(grade: number, discipline: string): Promise<INormative[]> {
        return (await axios.get<INormative[]>(API_URL, {
            params: {
                grade: String(grade),
                discipline: String(discipline)
            }
        })).data;
    }
}

export const normativeService = new NormativeService();