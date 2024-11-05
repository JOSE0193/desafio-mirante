import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cidade } from '@domain/cidade';
import { CidadePage } from '@domain/cidade-page';
import { Observable, first, from } from 'rxjs';

@Injectable()
export class ProjetoService {

    private readonly baseUrl = 'mirante/cidades';

    constructor(private readonly http: HttpClient) {}

    //------------------------------------------------
    /** Recupera a lista de cidades */
    //------------------------------------------------
    pesquisarCidades(page = 0, pageSize = 10): Observable<any> {
        return this.http.get<CidadePage>(this.baseUrl, {params: {page, pageSize}}).pipe(first());
    }

    //------------------------------------------------
    /** Exclui a cidade informada */
    //------------------------------------------------
    excluir(id: string): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`);
    }

    //------------------------------------------------
    /** Salva a cidade informada */
    //------------------------------------------------

    salvar(record: Partial<Cidade>): Observable<any>{
      if(record.id)  {
        return this.atualizarCidade(record);
      }
      return this.criarCidade(record);
    }

    private criarCidade(record: Partial<Cidade>) {
      return this.http.post<Cidade>(this.baseUrl, record).pipe(first());
    }

    private atualizarCidade(record: Partial<Cidade>) {
      return this.http.put<Cidade>(`${this.baseUrl}/${record.id}`, record).pipe(first());
    }

    //------------------------------------------------
    /** Lista de cidades para teste */
    //------------------------------------------------
    getListaCidadesMock() {
        return [
        {
          "id": 1,
          "nome": "Brasília",
          "uf": "DF",
          "capital": true
        },
        {
          "id": 2,
          "nome": "Goiânia",
          "uf": "GO",
          "capital": true
        },
        {
          "id": 3,
          "nome": "Salvador",
          "uf": "BA",
          "capital": true
        },
        {
          "id": 4,
          "nome": "Fortaleza",
          "uf": "CE",
          "capital": true
        },
        {
          "id": 5,
          "nome": "Curitiba",
          "uf": "PR",
          "capital": true
        },
        {
          "id": 6,
          "nome": "São Paulo",
          "uf": "SP",
          "capital": true
        },
        {
          "id": 7,
          "nome": "Rio de Janeiro",
          "uf": "RJ",
          "capital": true
        },
        {
          "id": 8,
          "nome": "Anápolis",
          "uf": "GO",
          "capital": false
        },
        {
          "id": 9,
          "nome": "Pouso Alegre",
          "uf": "MG",
          "capital": false
        },
        {
          "id": 10,
          "nome": "Volta Redonda",
          "uf": "RJ",
          "capital": false
        }
      ];
    }

};
