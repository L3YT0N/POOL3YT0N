package pe.edu.upeu.asistencia.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.modelo.Estudiante;
import pe.edu.upeu.asistencia.modelo.repositorio.EstudianteRepositorio;

import java.util.List;

@Service
public class EstudianteServicioImp extends EstudianteRepositorio implements EstudianteServicioI {

    @Override
    public void saveEntidad(Estudiante estudiante) {
        listaEstudiantes.add(estudiante);
    }

    @Override
    public List<Estudiante> findAllEntidades() {
        return listaEstudiantes;
    }

    @Override
    public void updateEntidad(Estudiante estudiante, int index) {
        listaEstudiantes.set(index,estudiante);
    }

    @Override
    public void deleteEntidad(int index) {
        listaEstudiantes.remove(index);
    }

    @Override
    public Estudiante findEntidad(int index) {
        return listaEstudiantes.get(index);
    }
}
