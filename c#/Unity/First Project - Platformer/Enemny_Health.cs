﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Enemny_Health : MonoBehaviour {

    void Update()
    {
        if (gameObject.transform.position.y < -8)
        {
            Destroy(gameObject);
        }
    }

}
