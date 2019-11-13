class Object{
    public:
    // Pure virtual functions (Fuck this)
    // No pure virtual anymore, bc casher
    // Acabo de notar que se podia usar la funcion amount en vez de queue,
    // pero no lo veo util
        virtual void print(char * nombre_archivo){ };
        virtual int  queue(){ return 0; };
        virtual void cleanQueue(){ };

        void open(){ };
        float amount(){ return 0; };
        void add(float cantidad){ };
};

class Printer : public Object
{
    public:
        void print(char * filename);
        int  queue();
        void cleanQueue();
};

class Plotter : public Object
{   
    public:
        void print(char * filename);
        int  queue();
        void cleanQueue();
};

class CashRegister : public Object
{
    public:
        void open();
        float amount();
        void add(float cantidad);
};

class Factory{
    public:
	Object *build(char type);
};