package com.code_room.interview_service.AudioStreamService.domain.usecases;

import com.code_room.interview_service.AudioStreamService.domain.model.Room;
import com.code_room.interview_service.AudioStreamService.domain.ports.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Servicio para la gestión de salas (rooms) utilizando Redis como almacenamiento.
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RedisTemplate<String, Room> redisTemplate;

    /**
     * Crea una nueva sala asociada a una entrevista.
     *
     * @param interviewId ID de la entrevista
     * @param participants Lista de IDs de participantes
     * @return ID generado de la sala
     */
    @Override
    public String createRoom(String interviewId, List<String> participants) {
        String roomId = UUID.randomUUID().toString();

        Room room = new Room();
        room.setRoomId(roomId);
        room.setParticipants(participants);

        redisTemplate.opsForValue().set(roomId, room);
        redisTemplate.opsForValue().set("interview:" + interviewId, room);

        return roomId;
    }

    /**
     * Obtiene una sala por su ID.
     *
     * @param roomId ID de la sala
     * @return Objeto Room o null si no existe
     */
    @Override
    public Room getRoom(String roomId) {
        return redisTemplate.opsForValue().get(roomId);
    }

    /**
     * Devuelve todos los usuarios en una sala, incluyendo entrevistador.
     *
     * @param roomId ID de la sala
     * @return Lista de IDs de usuarios
     */
    @Override
    public List<String> getUsersInRoom(String roomId) {
        Room room = getRoom(roomId);
        if (room == null) return Collections.emptyList();

        List<String> users = new ArrayList<>(room.getParticipants());
        if (room.getInterviewerId() != null) {
            users.add(room.getInterviewerId());
        }
        return users;
    }

    /**
     * Recupera el ID de sala asociado a una entrevista.
     *
     * @param interviewId ID de la entrevista
     * @return ID de la sala o null
     */
    @Override
    public String getRoomIdByInterview(String interviewId) {
        Room room = redisTemplate.opsForValue().get("interview:" + interviewId);
        return (room != null) ? room.getRoomId() : null;
    }


    /**
     * Verifica si un usuario está autorizado a ingresar a una sala.
     *
     * @param roomId ID de la sala
     * @param userId ID del usuario
     * @return true si el usuario es el entrevistador o un participante, false en caso contrario
     */
    @Override
    public boolean isUserAllowed(String roomId, String userId) {
        Room room = getRoom(roomId);
        if (room == null) return false;

        return userId.equals(room.getInterviewerId()) || room.getParticipants().contains(userId);
    }

    /**
     * Elimina una sala del sistema.
     *
     * @param roomId ID de la sala
     */
    @Override
    public void deleteRoom(String roomId) {
        redisTemplate.delete(roomId);
    }
}
