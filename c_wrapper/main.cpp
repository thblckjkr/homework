#include "wrapper.h"
#include <iostream> // cout

int main(){
    int t; float q; char * archivo;
    Object * o;
    Factory * f;
    f = new Factory;

    while (1 == (1 != -1)){
        std::cout<<"Que es lo que desea hacer?\n\t1)Imprimir algo\n\t2)Trabajar en la caja registradora\n";
        std::cin>>t;

        switch(t){
            case 1:
                std::cout<<"Plotter (1) o impresora (2)?\n";
                std::cin>>t;
                switch(t){
                    case 1:
                        o = f->build('l');
                        break;
                    case 2:
                        o = f->build('r');
                        break;
                }
                std::cout<<"Que desea hacer?\n1)Imprimir\n2)Ver cola\n3)Borrar cola\n";
                std::cin>>t;
                switch(t){
                    case 1:
                        std::cout<<"Escribe el nombre del archivo a imprimir\n";
                        std::cin>>archivo;
                        o->print(archivo);
                        break;
                    case 2:
                        std::cout<<"Hay "<<o->queue()<<" archivos en la cola\n";
                        break;
                    case 3:
                        o->cleanQueue();
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                o = f->build('c');
                std::cout<<"1)Abrir caja\n2)Cantidad a pagar\n3)Agregar monto\n";
                std::cin>>t;
                switch(t){
                    case 1:
                        o->open();
                        break;
                    case 2:
                        std::cout<<"Total a pagar: "<<o->amount()<<"\n";
                        break;
                    case 3:
                        std::cout<<"Escribe el monto a agregar\n";
                        std::cin>>q;
                        o->add(q);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    return 0;
}