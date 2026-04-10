package ru.aston.filler;

import ru.aston.entity.Student;

import java.util.List;

public class FillerContext {
    private DataFiller dataFiller;

    public  FillerContext (DataFiller dataFiller){
        this.dataFiller=dataFiller;
    }

    public DataFiller getDataFiller(){
        return dataFiller;
    }

    public void setDataFiller(DataFiller dataFiller) {
        this.dataFiller = dataFiller;
    }

    public List<Student> fill(int size) {
        if (dataFiller == null) {
            throw new IllegalStateException("DataFiller is not set.");
        }
        return dataFiller.fill(size);
    }
}