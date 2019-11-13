#include "base.h"
#include "wrapper.h"
#include <iostream> // std::cout

Object *Factory::build(char type) {
    switch (type)
    {
        case 'r':
            return new Printer;
            break;
        case 'l':
            return new Plotter;
            break;
        case 'c':
            return new CashRegister;
        default:
            break;
    }
};

/*
* Printer
*/
void Printer::print(char * filename){
    ImprimeImpresora(filename);
}
int Printer::queue(){
    return numeroTrabajosEsperaImpresora();
}
void Printer::cleanQueue(){
    LimpiaPrintingQueueImpresora();
}

/*
* Plotter
*/
void Plotter::print(char * filename){
    ImprimePlotter(filename);
}
int Plotter::queue(){
    return numeroTrabajosEsperaPlotter();
}
void Plotter::cleanQueue(){
    LimpiaPrintingQueuePlotter();
}

/*
* CashRegister
*/
void CashRegister::open(){
    AbreCajaRegistradora();
}
float CashRegister::amount(){
    return totalAPagar();
}
void CashRegister::add(float cantidad){
    EstableceMontoAPagar(cantidad);
}
