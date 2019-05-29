using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BGMovement : MonoBehaviour {

    public GameObject g;

    private void Start()
    {
        g = gameObject;
    }


    // Update is called once per frame
    void Update () {
        g.transform.position = new Vector3(Camera.main.transform.position.x, g.transform.position.y, g.transform.position.z);
	}
}
